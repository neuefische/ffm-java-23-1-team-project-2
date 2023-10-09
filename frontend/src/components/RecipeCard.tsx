type RecipeCardProps = {

    recipe : Recipe
}


export default function RecipeCard(props : RecipeCardProps) {

    return (
        <article>
            <h2>{props.recipe.title}</h2>
            <p>{props.recipe.description}</p>
        </article>
    )
}