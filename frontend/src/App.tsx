
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
            .catch(() => {
                alert('Fehler: Daten konnten nicht geladen werden. Komm später wieder.')
            })
    }

    function deleteRecipe (id: string) {
        axios.delete("/api/recipes/" + id)
            .then(() => {
                setRecipes(recipes?.filter((recipes) => recipes.id !==id))
            })
    }

    return (
        <>
            <Routes>
                <Route path="/" element={<RecipeGallery recipes={recipes} onDelete={deleteRecipe}/>}/>
                <Route path="/recipes/add" element={<AddRecipe uri={uri} getAll={getAll}/>}/>
                <Route path="/*" element={<RecipeGallery recipes={recipes} onDelete={deleteRecipe}/>}/>
            </Routes>
        </>
    )

}

