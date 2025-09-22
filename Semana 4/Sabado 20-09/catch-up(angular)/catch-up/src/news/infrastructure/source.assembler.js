import {LogoApi} from "@/shared/infrastructure/logo-api.js";
import {Source} from "@/news/domain/mode/source.entity.js";

const logoApi = new LogoApi();

export class SourceAssembler {
    static toEntityFromResource(resource) {
        let source = new Source({...resource});
        source.urlToLogo = source.url !== '' ? logoApi.getUrlToLogo(source) : '';
        return source;
    }
    
    static toEntitiesFromResponse(response) {
        if (response.data.status !== "ok") {
            console.log(`${response.status}, ${response.code}, ${response.message}`);
            return [];
        } 
        console.log(response.data)
        const sourcesResponse = response.data;
        return sourcesResponse.sources.map((source) =>{
            return this.toEntityFromResource(source);
        });
    }
}