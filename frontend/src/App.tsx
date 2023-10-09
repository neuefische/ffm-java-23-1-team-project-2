
import {useEffect, useState} from "react";
import axios from "axios";
import RecipeGallery from "./components/RecipeGallery.tsx";


export default function App() {

    const [recipes, setRecipes] = useState<Recipe[]>()
    const uri: string = "/api/recipes"

    useEffect(() => {
        getAll()
    }, []);

    function getAll() {
        axios.get(uri)
            .then(respond => {
                setRecipes(respond.data)
            })
            .catch((error) => error.message("error gefunden"))
    }

    return (
        <>
            {recipes && <RecipeGallery recipes={recipes}/>}
        </>
    )
}


