const UserMode = {
    CUSTOMER: "CUSTOMER",
    COURIER: "COURIER"
}

const defaultState = {
    login: "",
    name: "",
    surname: "",
    userMode: undefined,
}

export const userReducer = (state = defaultState, action) => {
    switch (action.type) {
        case "SET_USER":
            console.log(state)
            return {
                ...state, name: state.name,
                login: state.login,
                surname: state.surname,
                userMode: state.userMode,
            }
        default:
            console.log(state)
            return state;
    }
}