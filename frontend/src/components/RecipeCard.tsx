import {useNavigate} from "react-router-dom";

type RecipeCardProps = {

    recipe : Recipe
    onDelete: (id: string)  => void
    setRecipe: (recipe: Recipe) => void;
}


export default function RecipeCard(props : RecipeCardProps) {

    const navigate = useNavigate();

    const onHandleDelete = (id: string) => {
        props.onDelete(id);
    };
    function onHandleEdit (recipe: Recipe) {
        props.setRecipe(recipe)
        navigate(`/recipes/${props.recipe.id}/edit`)
    }

    return (
        <>
        <article>

            <h2>{props.recipe.title}</h2>
            <p>{props.recipe.description}</p>
            <button onClick={() => props.recipe.id && onHandleDelete(props.recipe.id)}>Delete</button>
            <button onClick={() => props.recipe && onHandleEdit(props.recipe)}>
                Edit
            </button>
        </article>
        </>
    )
}