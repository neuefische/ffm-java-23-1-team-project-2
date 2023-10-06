import RecipeCard from "./RecipeCard.tsx";

type RecipeGalleryProps = {
    recipes: Recipe[]
}


export default function RecipeGallery(props: RecipeGalleryProps) {


    return (

        <>
            <h2>
                RecipeGallery
            </h2>
            {props.recipes.map(
                recipe =>
                    <RecipeCard key={recipe.id} recipe={recipe}/>
            )}
        </>

    )

}