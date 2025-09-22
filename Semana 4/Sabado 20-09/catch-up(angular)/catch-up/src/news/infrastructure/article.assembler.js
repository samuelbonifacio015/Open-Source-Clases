import {Article} from "@/news/domain/mode/article.entity.js";
import {SourceAssembler} from "@/news/infrastructure/source.assembler.js";

export class ArticleAssembler {
    static source = null;
    
    static withSource(source) {
        this.source = source;
        return this;
    }
    
    static toEntityFromResource(resource) {
        let article = new Article(resource);
        article.source = this.source && this.source.id === resource.source.id ? this.source : 
        SourceAssembler.toEntityFromResource(resource.source);
        return article;
    }
    
    static toEntitiesFromResponse(response) {
        if (response.data.status !== "ok") {
            console.log(`${response.status}, ${response.code}, ${response.message}`);
            return [];
        } 
        const articlesResponse = response.data;
        return articlesResponse["articles"].map((article) =>{
            return this.toEntityFromResource(article);
        });
    }
}