import RecipeCard from "./RecipeCard.tsx";
import Header from "./Header.tsx";
import {Link} from "react-router-dom";

type RecipeGalleryProps = {
    recipes?: Recipe[]
    onDelete: (id: string) => void;
}
export default function RecipeGallery(props: RecipeGalleryProps) {

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080':window.location.origin
        window.open(host + '/oauth2/authorization/github', '_blank')
    }

    return (
        <div className="div_gallery">
            <Header/>
            <button onClick={login}>Login</button>
            <Link to={"/recipes/add"}>New</Link>
            {props.recipes?.map((recipe) => (
                <div key={recipe.id}>
                    <RecipeCard onDelete={props.onDelete} recipe={recipe}/>
                </div>
            ))}
        </div>
    );
}