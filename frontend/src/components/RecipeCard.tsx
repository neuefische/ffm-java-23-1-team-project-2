type RecipeCardProps = {

    recipe : Recipe
    onDelete: (id: string)  => void
}


export default function RecipeCard(props : RecipeCardProps) {

    const onHandleDelete = (id: string) => {

        id && props.onDelete(id);
    };

    return (
        <article>
            <h2>{props.recipe.title}</h2>
            <p>{props.recipe.description}</p>
            <button onClick={() => props.recipe.id && onHandleDelete(props.recipe.id)}>Delete</button>
        </article>
    )
}