import axios from 'axios';

export default {
    acceptableCodes : [200,201,202,203,204,205],

    createFlashcard(flashcard){
        return axios.post('/flashcard', flashcard);
    },

    getCards(){
        return axios.get('/flashcards');
        
    },

    updateFlashcard(flashcard){
        return axios.put('/flashcard', flashcard);
    }

}