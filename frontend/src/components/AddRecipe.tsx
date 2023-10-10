import {FormEvent, useState} from "react";

type AddRecipeProps ={

    saveRecipe: (newRecipe: Recipe) => void;

}

export default function AddRecipe(props: AddRecipeProps){

    const[newRecipe, setnewRecipe] = useState()
    function onFormSubmit(event: FormEvent<HTMLFormElement>){

    }

    return(
    <>
        <form onSubmit={onFormSubmit}>
            <label>
                Titel
            </label>
            <input/>
            <label>
                Description
            </label>
            <input/>
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