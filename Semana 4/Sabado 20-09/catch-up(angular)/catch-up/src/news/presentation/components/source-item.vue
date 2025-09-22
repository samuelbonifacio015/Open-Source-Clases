<script setup lang="js">

import {Source} from "@/news/domain/mode/source.entity.js";

const props = defineProps({ source: { type: Source,  required: true } });
const emit = defineEmits(['source-select']);

function emitSourceSelectEvent(){
    emit('source-select', props.source);
}
</script>

<template>
  <div class="m-4">
    <div @click="emitSourceSelectEvent" class="flex align-content-start flex-wrap">
      <span class="flex align-items-center justify-content-center mr-2">
        <pv-avatar :aria-label="source.name"
                   :image="source.urlToLogo"
                   shape="circle"/>
      </span>
      <span class="flex align-items-center justify-content-center">
        {{ source.name }}
      </span>       
    </div>
  </div>

</template>

<style scoped>

</style>