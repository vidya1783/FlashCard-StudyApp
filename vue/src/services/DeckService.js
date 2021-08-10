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
    getFlashcardsByDeckId(id){
        return axios.get(`/deckcards/${id}`)
    },

    getFlashcardsNotinDeckId(id){
        return axios.get(`/notindeckcards/${id}`)
    },
    attachCardToDeck(deckId, flashcardId){
        return axios.post(`deck/${deckId}/${flashcardId}`)
    },
    detachCardFromDeck(deckId, flashcardId){
        return axios.delete(`deck/${deckId}/${flashcardId}`)
    }

}