<template>
  <div>
<!-- vue router code if it gets overwritten:
      { path: '/tags',
      name: 'tag-list',
      component: Tags,
      meta: {requiresAuth: true}}
      -->
<!-- Write UI code here, copying from the "debug/demo area below"                             --->


<!-- Debug/Demo Area
  
  
Please do not change data access code below this line.
The "ugly" debug code can be hidden by changing debug to equal true.

to create a UI, copy and paste code and ensure that it works:
1) It displays in the browser
2) No "red" errors appear in the Vue console
 -->
<div v-if="debug">
      We're in the tag component.

<!-- {{tagListRaw}}

{{tagList}} -->
<div>
  <p>
 <!--  <v-select :options="tagList" v-model="selectedTags" multiple></v-select>
     {{selectedTags}} -->
     test
</P>
</div>

<div>


</div>
<v-btn></v-btn>
<v-container>

<!-- <v-select :value.sync="selectedTags" :text="selectedTags"
:options="selectedTags"></v-select> -->


</v-container>
</div>    

    </div>

</template>

<script>
import cardService from '../services/FlashcardService';
import tagService from '../services/TagService';
// import vSelect from 'vue-select';
// import 'vue-select/dist/vue-select.css';

export default {
  name: 'tagComponent',
//  components: {vSelect},
components: {},
  props: { },
  watchInt: 0,
  data() {
    return {
      debug: true,
      cardListRaw: [],
      tagListRaw: [],
      selectedTags: [],
      joeTest: ["0","1","2"]
    }
  },
  methods:
  {
    errorSituation() {
      console.log("oops");
    },
    theText(item) {
      return item.tag_text;
    }

   },
  watch: {
     correctPresses: {
       handler() {
         let correctFiltered = this.correctAnswers.filter(x => x==true);
         let correctCount = correctFiltered.length;
         this.currentScore = correctCount;
       }
     }

  },
  computed: 
  {
    tagList() {
      return this.tagListRaw.map(tag => tag.tag_text);
    }
  },
  mounted() {
    let instance = this;

    function flashcardList(instancePassed) {
      cardService.getCards().then(
      response => {
        if (cardService.acceptableCodes.includes(response.status))
        {instancePassed.cardListRaw = response.data;}
        }
      ).catch((error) => {
  console.error(error); console.log("oops");});
    }

    function tagList(instancePassed)
    {
      tagService.getAllTags().then(
        response => {
          if (tagService.acceptableCodes.includes(response.status))
          {
            instancePassed.tagListRaw = response.data;
            }
        }
      ).catch((error) => {
  console.error(error);});
    }

    flashcardList(instance);
    tagList(instance);

  },
  updated() {
  }

  
}
</script>

<style scoped>
.nostyleplease {
  display:text;
}
</style>