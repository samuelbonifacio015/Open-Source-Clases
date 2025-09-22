import {Source} from "@/news/domain/mode/source.entity.js";
import {reactive} from "vue";
import {SourceAssembler} from "@/news/infrastructure/source.assembler.js";
import {ArticleAssembler} from "@/news/infrastructure/article.assembler.js";
import {NewsApi} from "@/news/infrastructure/news-api.js";

const newsApi = new NewsApi()

export const newsStore = reactive({
    sources: [],
    articles: [],
    errors: [],
    currentSource: null,
    setCurrentSource(source){
        if (source instanceof Source) {
            this.currentSource = source;
        }
    },

    loadSources() {
        this.errors=[];
        newsApi.getSources().then(response => {
            console.log("NES-STORE",response);
            this.sources = SourceAssembler.toEntitiesFromResponse(response);
            console.log("NES-sources",this.sources);
            if (this.sources.length > 0){
                this.setCurrentSource(this.sources[0]);
            }
        }).catch(error => {
            this.errors.push(error);
            this.sources=[];
        });
    },
    loadArticlesForCurrentSource() {
        if (this.currentSource == null) return;
        newsApi.getArticlesForSourceId(this.currentSource.id).then(response => {
            this.articles = ArticleAssembler.withSource(this.currentSource.id).toEntitiesFromResponse(response);
        }).catch(error => {
            this.errors.push(error);
            this.articles=[];
        });
    }
});