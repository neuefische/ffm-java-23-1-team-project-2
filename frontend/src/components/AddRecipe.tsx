import {ChangeEvent, FormEvent, useState} from "react";
import Header from "./Header.tsx";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

type AddRecipeProps = {
    uri: string,
    getAll: () => void
}

export default function AddRecipe(props: AddRecipeProps) {

    const navigate = useNavigate()

    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")

    function saveRecipe(newRecipe: Recipe) {

        axios.post(props.uri, newRecipe)
            .then(props.getAll)
            .then(() => navigate("/"))
            .catch((error) => {
                alert('Fehler:' + error.response.data)
                navigate("/recipes/add")
            })
    }

    function onFormSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        const recipe: Recipe = {
            title: title,
            description: description
        }
        saveRecipe(recipe)
    }


    function onTitleChange(event: ChangeEvent<HTMLInputElement>) {
        setTitle(event.target.value)
    }

    function onDescriptionChange(event: ChangeEvent<HTMLTextAreaElement>) {
        setDescription(event.target.value)
    }

    return (
        <>
            <Header/>
            <form onSubmit={onFormSubmit}>
                <label>
                    Titel
                </label>
                <input name="title" value={title} onChange={onTitleChange}/>
                <label>
                    Description
                </label>
                <textarea name="description" value={description} onChange={onDescriptionChange}/>
                <button>
                    Save
                </button>
            </form>
            <Link to={"/"}>
                Back
            </Link>
        </>
    )
}