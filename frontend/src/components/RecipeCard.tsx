type RecipeCardProps = {

    recipe : Recipe
}


export default function RecipeCard(props : RecipeCardProps) {

    return (

        <>
        <h2>
            {props.recipe.id}
            {props.recipe.title}
            {props.recipe.description}
        </h2>
        </>
    )
}