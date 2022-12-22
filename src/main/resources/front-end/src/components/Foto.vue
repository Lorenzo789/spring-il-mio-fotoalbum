<template>
    <main>
        <h1 class="text-center mt-3">Foto</h1>
        
        <div class="d-flex justify-content-center mt-4">
            <div class="col-auto me-2">
                <input type="text" class="form-control" placeholder="Search" v-model="searchedQuery">
            </div>
            <button class="btn btn-success" @click="getSearchedFoto()">Search</button>
        </div>

        <div class="container mt-5">
            <div class="ms_height" v-for="foto in fotos" :key="foto.id">
                <div class="card mb-3" v-if="foto.visible">

                    <div class="row g-0">
                        <div class="col-md-6">
                            <img :src=foto.url alt="">
                        </div>
                        <div class="col-md-6">
                            <div class="card-body">
    
                                <h5 class="card-title">{{ foto.title }}</h5>
    
                                <p class="card-text">
                                    <strong>Desc:</strong>
                                    {{ foto.description }}
                                </p>
    
                                <p class="card-text">
                                    #{{ foto.tag }}
                                </p>
    
                                <strong>Categories:</strong>
    
                                <span v-for="category in foto.categories" :key="category.id">
                                    - {{ category.name }}
                                </span>
    
                                <button class="d-block my-3 btn btn-primary" @click="findActiveFotoId(foto.id)">
                                    Add a comment
                                </button>
    
                                <div>
                                    <ul class="ms_height_max ms_overflow">
                                        <li v-for="comment in foto.comments" :key="comment.id">
                                            {{ comment.text }}
                                        </li>  
                                    </ul>
                                </div>
    
                                <div v-if="activeFotoCommentId == foto.id" class="d-flex mt-4">
                                    <div class="col-auto me-2">
                                        <input type="text" placeholder="Add A Comment" v-model="comment" class="form-control">
                                    </div>
                                    <button @click="createNewComment(foto.id)" class="btn btn-success">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- <div class="px-5 mt-5">
            <div v-for="foto in fotos" :key="foto.id" class="ms_card">
                <div>
                    <img :src=foto.url alt="">
                    <h3>{{ foto.title }}</h3>
                    <p>Description: {{ foto.description }}</p>
                    <h5>Tags: {{ foto.tag }}</h5>
                    <strong>Categories:</strong>
                    <span v-for="category in foto.categories" :key="category.id">
                        - {{ category.name }}
                    </span>
                    <button @click="findActiveFotoId(foto.id)">
                        Add a comment
                    </button>
                    <div v-for="comment in foto.comments" :key="comment.id">
                          <p>{{ comment.text }}</p>  
                    </div>

                    <div v-if="activeFotoCommentId == foto.id">
                        <input type="text" placeholder="Add A Comment" v-model="comment">
                        <button @click="createNewComment(foto.id)" class="btn btn-success">Add</button>
                    </div>
                </div>
            </div>
        </div> -->

    </main>

</template>

<script>
import axios from "axios";

const URL_FOTO = "http://localhost:8080/api/1/foto";
const URL_COMMENTS = "http://localhost:8080/api/1/comment";

export default {
    name: 'Foto',

    data(){
        return{
            fotos: [],
            searchedQuery: '',
            comment: "",
            activeFotoCommentId: -1,
        };
    },

    methods: {
        getSearchedFoto(){

            if (this.searchedQuery === '') {
                return this.getAllFoto();
            }

            axios.get(URL_FOTO + '/search/' + this.searchedQuery)
            .then((response) => {
                this.fotos = response.data;
            })
            .catch((error) => {
                console.log(error);
            })
        },

        getAllFoto(){
            axios.get( URL_FOTO + '/all')
            .then((response) => {
                this.fotos = response.data;
                console.log(response.data);
            })
            .catch((error) =>{
                console.log(error);
            })
        },

        createNewComment(id){
            console.log(this.comment, id)
            
            axios.post(URL_COMMENTS + '/create/' + id + '/' + this.comment)
            .then((response) => {
                const newComment = response.data;
                this.getAllFoto();
            })
            .catch((error) => {
                console.log(error);
            })
            this.comment = "";
        },

        findActiveFotoId(id){
            this.activeFotoCommentId = id;
        }
    },

    created(){
        this.getAllFoto();
    }
}
</script>

<style lang="scss" scoped>
.ms_overflow{
    overflow-y: auto;
}

.ms_img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.ms_height_max{
    max-height: 70px;
}

.ms_height{
    height: 300px;
}
</style>