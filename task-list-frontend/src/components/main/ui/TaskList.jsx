import {Card} from "./Card.jsx";
import {taskTypes} from "../../../config.jsx";
import {useTasks} from "../hooks/useTasks.jsx";

export const TaskList = () => {
    const { tasks, isLoading, error, updateTask, createTask, deleteTask } = useTasks();

    if (isLoading) return <div className="text-center mt-4">Loading tasks...</div>;
    if (error) return <div className="text-center mt-4 text-danger">{error}</div>;

    return (
        <div className="row justify-content-center mt-4 mx-5">
            {taskTypes.map(type => (
                <Card key={type}
                      type={type}
                      tasks={tasks.filter(task => task.status === type)}
                      onTaskUpdate={updateTask}
                      onTaskCreate={createTask}
                      onTaskDelete={deleteTask}
                />
            ))}
        </div>
    );
};

