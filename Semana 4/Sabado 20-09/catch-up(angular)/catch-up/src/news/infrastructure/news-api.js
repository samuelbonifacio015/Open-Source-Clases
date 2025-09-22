import axios from 'axios';

const newApiUrl = import.meta.env.VITE_NEWS_API_URL;
const apiKey = import.meta.env.VITE_NEWS_API_KEY;
const sourceEndpoint = import.meta.env.VITE_SOURCES_ENDPOINT_PATH;
const topHeadLinesEndpoint = import.meta.env.VITE_TOP_HEADLINES_ENDPOINT_PATH;

const http = axios.create({
    baseURL: newApiUrl,
    params: {apikey: apiKey},
})

export class NewsApi {
    getSources() {
        return http.get(`${sourceEndpoint}`);
    }
    
    getArticlesForSourceId(sourceId) {
        return http.get(topHeadLinesEndpoint, {params: {sources: sourceId}});
    }
}