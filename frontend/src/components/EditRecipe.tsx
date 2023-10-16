import Header from "./Header.tsx";

type EditRecipesProps ={

    recipe: Recipe | undefined

}
export default function EditRecipe(props: EditRecipesProps){

    return(

        <>
            <Header/>
            <form>
            <input value={props.recipe?.title}/>
            <textarea value={props.recipe?.description}/>
                <button type="submit">
                    Save (geht noch nicht)
                </button>
            </form>
        </>
    )
}