import axios from 'axios';

export default {
    acceptableCodes : [200,201,202,203,204,205],

    getAllTags() {
        return axios.get('/tags');
    },

    updateCardsTags(flashcardId, tagObjects) {
        return axios.put('/flashcard/' + flashcardId + "/tags", tagObjects);
    },

    getTagsForCard(flashcardId) {
        return axios.get('/flashcard/' + flashcardId + "/tags");
    }

}