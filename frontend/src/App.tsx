
import {useEffect, useState} from "react";
import axios from "axios";
import RecipeGallery from "./components/RecipeGallery.tsx";
import Header from "./components/Header.tsx";
import AddRecipe from "./components/AddRecipe.tsx";



export default function App() {

    const [recipes, setRecipes] = useState<Recipe[]>()
    const uri: string = "/api/recipes"

    useEffect(() => {
        getAll()
    }, []);

    function getAll() {
        axios.get(uri)
            .then(response => {setRecipes(response.data)
            })
            .catch((error) => error.message("error gefunden"))
    }

    function saveRecipe(newRecipe: Recipe){
        axios.post(uri, newRecipe)
            .then(response =>setRecipes(response.data))
            .catch((error) => error.message("error can not save"))
        }

    return (
        <>
            <Header/>
            <AddRecipe saveRecipe={saveRecipe}/>
            {recipes && <RecipeGallery recipes={recipes}/>}
        </>
    )

}

