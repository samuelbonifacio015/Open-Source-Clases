const logoApiUrl = import.meta.env.VITE_LOGO_API_URL;

export class LogoApi {
    getUrlToLogo(source) { 
        return `${logoApiUrl}/${new URL(source.url).hostname}`;
    }
}

