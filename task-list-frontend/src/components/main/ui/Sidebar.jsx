import React, {useEffect, useState} from 'react';
import styles from "../styles/Main.module.scss";
import {taskTypes} from "../../../config.jsx";

export const Sidebar = ({ show, task, onClose, onSave }) => {
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        status: taskTypes[0]
    });

    const [isSubmitting, setIsSubmitting] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setFormData({
            title: task?.title || '',
            description: task?.description || '',
            status: task?.status || taskTypes[0]
        });
        setError(null);
    }, [task]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async () => {
        if (!formData.title.trim()) {
            setError('Title is required');
            return;
        }

        try {
            setIsSubmitting(true);
            setError(null);

            const taskData = {
                ...formData,
                ...(task?.id && { id: task.id })
            };

            await onSave(taskData);
            onClose();
        } catch (error) {
            setError('Failed to save task. Please try again.');
            console.error('Error saving task:', error);
        } finally {
            setIsSubmitting(false);
        }
    };

    if (!show) return null;

    return (
        <div className={styles.sidebar}>
            <div className="p-4">
                <div className="d-flex justify-content-between align-items-center mb-4">
                    <h4 className="m-0">{task ? 'Edit Task' : 'Create New Task'}</h4>
                </div>

                {error && (
                    <div className="alert alert-danger" role="alert">
                        {error}
                    </div>
                )}

                <div className="mb-3">
                    <input
                        type="text"
                        name="title"
                        className="form-control"
                        placeholder="Task Title"
                        value={formData.title}
                        onChange={handleChange}
                        disabled={isSubmitting}
                    />
                </div>

                <div className="form-floating mb-3">
                    <textarea
                        name="description"
                        className="form-control"
                        placeholder="Task description"
                        id="taskDescription"
                        value={formData.description}
                        onChange={handleChange}
                        disabled={isSubmitting}
                        style={{ height: '120px' }}
                    />
                    <label htmlFor="taskDescription">Description</label>
                </div>

                <div className="form-floating mb-4">
                    <select
                        name="status"
                        className="form-select"
                        value={formData.status}
                        onChange={handleChange}
                        disabled={isSubmitting}
                    >
                        {taskTypes.map((taskType) => (
                            <option key={taskType} value={taskType}>
                                {taskType.replace('_', ' ')}
                            </option>
                        ))}
                    </select>
                    <label>Status</label>
                </div>

                <div className="d-flex justify-content-end gap-2">
                    <button
                        className="btn btn-secondary"
                        onClick={onClose}
                        disabled={isSubmitting}
                    >
                        Cancel
                    </button>
                    <button
                        className="btn btn-primary"
                        onClick={handleSubmit}
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? (
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true" />
                                Saving...
                            </>
                        ) : 'Save'}
                    </button>
                </div>
            </div>
        </div>
    );
};