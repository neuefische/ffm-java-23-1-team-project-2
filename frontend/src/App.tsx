
import {useEffect, useState} from "react";
import axios from "axios";
import RecipeGallery from "./components/RecipeGallery.tsx";
import {Route, Routes} from "react-router-dom";
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



    return (
        <>
            <Routes>
                <Route path="/" element={<RecipeGallery recipes={recipes}/>}/>
                <Route path="/recipes/add" element={<AddRecipe uri={uri} getAll={getAll}/>}/>
            </Routes>
        </>
    )

}

