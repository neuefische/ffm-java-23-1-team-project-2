import RecipeCard from "./RecipeCard.tsx";
import Header from "./Header.tsx";
import {Link} from "react-router-dom";

type RecipeGalleryProps = {
    recipes?: Recipe[]
    onDelete: (id: string) => void;
}
export default function RecipeGallery(props: RecipeGalleryProps) {

    return (
        <div className="div_gallery">
            <Header/>
            <Link to={"/recipes/add"}>New</Link>
            {props.recipes?.map((recipe) => (
                <div key={recipe.id}>
                    <RecipeCard onDelete={props.onDelete} recipe={recipe}/>
                </div>
            ))}
        </div>
    );
}