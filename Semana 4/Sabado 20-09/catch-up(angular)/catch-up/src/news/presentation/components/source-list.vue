<script setup>
import {Source} from "@/news/domain/mode/source.entity.js";
import SourceItem from "@/news/presentation/components/source-item.vue";

const props = defineProps({ visible: Boolean, sources: Array[Source] });
const emit = defineEmits(['source-selected']);

function emitSourceSelectedEvent(source){
  emit('source-selected', source);
}
</script>

<template>
  <pv-drawer v-bind:visible="visible">
    <source-item  v-for="source in sources" 
                  :key="source.name" 
                  :source="source" 
                  @source-select="emitSourceSelectedEvent(source)"/>
  </pv-drawer>
  
</template>

<style scoped>

</style>