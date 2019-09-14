<template>
<div class="about">
    <SearchRange msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import SearchRange from '@/components/SearchRange.vue'

export default {
  name: 'third',
  components: {
    SearchRange
  }
}
</script>
