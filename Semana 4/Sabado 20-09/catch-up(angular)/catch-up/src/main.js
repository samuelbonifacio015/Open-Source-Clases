import './style.css'

import { createApp } from 'vue'
import App from './App.vue'
import i18n from "./i18n.js";
import PrimeVue from 'primevue/config';
import Material from '@primeuix/themes/material'
import {Avatar, Button, Drawer, Menubar, SelectButton} from "primevue";

createApp(App)
    .use(i18n)
    .use(PrimeVue,{ ripple: true, theme: { preset: Material }} )
    .component('pv-menubar', Menubar)
    .component('pv-button', Button)
    .component('pv-select-button', SelectButton)
    .component('pv-avatar', Avatar)
    .component('pv-drawer', Drawer)
        .mount('#app')
