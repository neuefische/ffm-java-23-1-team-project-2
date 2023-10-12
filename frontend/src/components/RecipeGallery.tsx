import RecipeCard from "./RecipeCard.tsx";
import Header from "./Header.tsx";
import {Link} from "react-router-dom";

type RecipeGalleryProps = {
    recipes?: Recipe[]
    onDelete: (id: string) => void;
}
export default function RecipeGallery(props: RecipeGalleryProps) {
    const handleDelete = (id: string) => {

        props.onDelete(id);
    };
    return (
        <div className="div_gallery">
            <Header/>
            <Link to={"/recipes/add"}>New</Link>
            {props.recipes?.map((recipe) => (
                <div key={recipe.id}>
                    <RecipeCard recipe={recipe}/>
                    <button onClick={() => recipe.id && handleDelete(recipe.id)}>Delete</button>
                </div>
            ))}
        </div>
    );
}