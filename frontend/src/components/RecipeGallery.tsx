import RecipeCard from "./RecipeCard.tsx";
import Header from "./Header.tsx";
import {Link} from "react-router-dom";

type RecipeGalleryProps = {
    recipes?: Recipe[]
}
export default function RecipeGallery(props: RecipeGalleryProps) {
    return (
        <div className="div_gallery">
            <Header/>
            <Link to={"/recipes/add"}>
                New
            </Link>
            {props.recipes && props.recipes.map(
                recipe =>
                    <RecipeCard key={recipe.id} recipe={recipe}/>
            )}
        </div>
    )
}