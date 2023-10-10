import RecipeCard from "./RecipeCard.tsx";

type RecipeGalleryProps = {
    recipes: Recipe[]
}


export default function RecipeGallery(props: RecipeGalleryProps) {

    console.log(props.recipes[0])
    return (

        <div className="div_gallery">
            {props.recipes.map(
                recipe =>
                    <RecipeCard key={recipe.id} recipe={recipe}/>
            )}
        </div>

    )

}