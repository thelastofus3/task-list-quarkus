import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Main} from "../main/Main.jsx";
import {Login} from "../auth/Login.jsx";
import {Registration} from "../auth/Registration.jsx";

export const Router = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Main/>} />
                <Route path="/login" element={<Login/>}></Route>
                <Route path="/registration" element={<Registration/>}></Route>
            </Routes>
        </BrowserRouter>
    );
};