<script lang="js" setup>
import {computed, onMounted, ref} from "vue";
import FooterContent from "@/shared/presentation/components/footer-content.vue";
import LanguageSwitcher from "@/shared/presentation/components/language-switcher.vue";
import {newsStore} from "@/news/application/news.store.js";
import SourceList from "@/news/presentation/components/source-list.vue";

const drawerVisible = ref(false);
const toggleDrawer = () => {
    drawerVisible.value = !drawerVisible.value;
 };

const sources = computed(() => newsStore.sources);
const errors = computed(() => newsStore.errors);
let articles = computed(() => newsStore.articles);
const rerenderKey = ref(0);
const setSource = source => {
  newsStore.setCurrentSource(source);
  articles = computed(() => newsStore.articles);
  rerenderKey.value += 1;
  toggleDrawer()
};

onMounted(() => {
  newsStore.loadSources();
  rerenderKey.value += 1;
  console.log("DATA:",sources);
  console.log("DATA2:",newsStore.sources);
});

</script>

<template>
  <div>
    <div>
      <pv-menubar>
        <template #start>
          <pv-button icon="pi pi-bars" label="CatchUp" text @click="toggleDrawer"></pv-button>
          <source-list v-model:sources="sources" 
                       v-model:visible="drawerVisible" 
                       v-on:source-selected="setSource($event)"/>
        </template>
        <template #end>
          <language-switcher/>
        </template>         
      </pv-menubar>
    </div>
  </div>
  <footer-content></footer-content>
</template>

<style scoped>

</style>