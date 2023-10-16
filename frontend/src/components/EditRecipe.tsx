type EditRecipesProps ={

    recipe: Recipe

}
export default function EditRecipe(props: EditRecipesProps){

    return(

        <>
        <p>
            {props.recipe.title}
        </p>
        </>
    )
}