import { defineConfig } from 'vite'
import { fileURLToPath, URL } from 'node:url'
import vueDevTools from 'vite-plugin-vue-devtools'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()
      , vueDevTools()], 
    resolve: { alias: { '@': fileURLToPath(new URL('./src', import.meta.url))
    } 
  }
  

})
