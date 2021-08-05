import axios from 'axios';



export default {
    acceptableCodes : [200,201,202,203,204,205],
    getDecks(){
        return axios.get('/decks')
    },

    createDeck(deck) {
        return axios.post('/deck',deck)
    },

    updateDeck(deck) {
        return axios.put('/deck', deck)
    },




}