import {ChangeEvent, FormEvent, useState} from "react";

type AddRecipeProps ={
    setRecipe: (recipe: Recipe) => void;
    saveRecipe: (newRecipe: Recipe) => void;

}

export default function AddRecipe(props: AddRecipeProps){

    const[title, setTitle] = useState("")
    const[description, setDescription] = useState("")


    function onFormSubmit(event: FormEvent<HTMLFormElement>){
        event.preventDefault()
        const recipe: Recipe = {
            title: title,
            description: description
        }
        recipe && props.saveRecipe(recipe)
        recipe && props.setRecipe(recipe)
    }



    function onTitleChange(event: ChangeEvent<HTMLInputElement>){
        setTitle(event.target.value)
    }

    function onDescriptionChange(event: ChangeEvent<HTMLInputElement>) {
        setDescription(event.target.value)
    }

    return(
    <>
        <form onSubmit={onFormSubmit}>
            <label>
                Titel
            </label>
            <input name="title" value={title} onChange={onTitleChange}/>
            <label>
                Description
            </label>
            <input name="description" value={description} onChange={onDescriptionChange}/>
            <button>
                Save
            </button>
        </form>
        <button>
            Back
        </button>
    </>
    )
}