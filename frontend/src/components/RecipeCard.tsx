import {Link, Route, Routes} from "react-router-dom";
import EditRecipe from "./EditRecipe.tsx";

type RecipeCardProps = {

    recipe : Recipe
    onDelete: (id: string)  => void
}


export default function RecipeCard(props : RecipeCardProps) {

    const onHandleDelete = (id: string) => {
        props.onDelete(id);
    };

    return (
        <>
        <article>
            <h2>{props.recipe.title}</h2>
            <p>{props.recipe.description}</p>
            <button onClick={() => props.recipe.id && onHandleDelete(props.recipe.id)}>Delete</button>
            <Link to="/recipe/'${props.recipe.id}'/edit">
                Edit
            </Link>
        </article>
        <Routes>
            <Route path="/recipe/:id/edit" element={<EditRecipe recipe={props.recipe}/>}/>
        </Routes>
        </>
    )
}