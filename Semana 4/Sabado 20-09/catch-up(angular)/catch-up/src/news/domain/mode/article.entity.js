import {Source} from "@/news/domain/mode/source.entity.js";

export class Article {
    constructor({title = '', description = '', url = '', 
                    utlToImage = '', source = null, publishedAt = ''}) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.utlToImage = utlToImage;
        this.source = source ? new Source(source) : null;
        this.publishedAt = new Date(publishedAt);
    }
    
    getFormatedPublishedAt(){
        return this.publishedAt.toLocaleDateString('es-US', {
            year:  'numeric',
            month: '2-digit',
            day:   '2-digit',
            hour:  '2-digit',
            minute:'2-digit'
        });
    }
    
}