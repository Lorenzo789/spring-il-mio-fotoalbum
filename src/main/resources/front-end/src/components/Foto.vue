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
                </div>
            </div>
        </div>

    </main>

</template>

<script>
import axios from "axios";

const URL_FOTO = "http://localhost:8080/api/1/foto";

export default {
    name: 'Foto',

    data(){
        return{
            fotos: [],
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
            })
            .catch((error) =>{
                console.log(error);
            })
        }
    },

    created(){
        this.getAllFoto();
    }
}
</script>

<style lang="scss" scoped>

</style>