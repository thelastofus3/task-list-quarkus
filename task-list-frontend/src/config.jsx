export const registerConfig = [
    {
        name: "username",
        placeholder: "Username",
        type: "text"
    },
    {
        name: "email",
        placeholder: "E-mail",
        type: "email"
    },
    {
        name: "password",
        placeholder: "Password",
        type: "password"
    },
    {
        name: "matchingPassword",
        placeholder: "Confirm Password",
        type: "password"
    }
];
export const loginConfig = [
    {
        name: "email",
        placeholder: "E-mail",
        type: "email"
    },
    {
        name: "password",
        placeholder: "Password",
        type: "password"
    },
];

export const initialState = {
    username: "",
    email: "",
    password: "",
    matchingPassword: ""
};
