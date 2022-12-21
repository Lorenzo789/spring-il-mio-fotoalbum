<template>
    <main>
        <h1 class="text-center mt-3">Foto</h1>
    
        
        <div class="d-flex justify-content-center mt-4">
            <div class="col-auto me-2">
                <input type="text" class="form-control" placeholder="Search" v-model="searchedQuery">
            </div>
            <button class="btn btn-success" @click="getSearchedFoto()">Search</button>
        </div>


        <div class="d-flex justify-content-between container mt-5">
            <div v-for="foto in fotos" :key="foto.id">
                <div>
                    <img :src=foto.url alt="">
                    <h3>{{ foto.title }}</h3>
                    <p>Description: {{ foto.description }}</p>
                    <h5>Tags: {{ foto.tag }}</h5>
                    <strong>Categories:</strong>
                    <span v-for="category in foto.categories" :key="category.id">
                        - {{ category.name }}
                    </span>
                </div>
            </div>
        </div>

    </main>

</template>

<script>
import axios from "axios";

const URL_FOTO = "http://localhost:8080/api/1/foto";
const URL_FOTO_W_CATEGORY = "http://localhost:8080/api/1/category";

export default {
    name: 'Foto',

    data(){
        return{
            fotos: [],
            fotosWCategories: [],
            searchedQuery: '',
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

        getAllFotoWCategories(){
            axios.get( URL_FOTO_W_CATEGORY + '/all')
            .then((response) => {
                this.fotosWCategories = response.data;
                console.log(response.data);
            })
            .catch((error) =>{
                console.log(error);
            })
        }
    },

    created(){
        this.getAllFoto();
        this.getAllFotoWCategories();
    }
}
</script>

<style lang="scss" scoped>

</style>