import axios from 'axios';
const http = axios.create({
    baseURL: "http://localhost:8080"
});
export default{
    addFlashcard(flashcard){
        return http.post(  `/flashcard`, flashcard);
    }
}