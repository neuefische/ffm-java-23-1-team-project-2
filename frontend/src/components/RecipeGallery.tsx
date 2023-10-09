import RecipeCard from "./RecipeCard.tsx";

type RecipeGalleryProps = {
    recipes: Recipe[]
}


export default function RecipeGallery(props: RecipeGalleryProps) {


    return (

        <div className="div_gallery">
            <h1>
                RecipeGallery
            </h1>
            {props.recipes.map(
                recipe =>
                    <RecipeCard key={recipe.id} recipe={recipe}/>
            )}
        </div>

    )

}