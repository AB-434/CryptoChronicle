import React from 'react';

export default function Cards({ img, heading, role }) {
    return (
        <div className="testimonial-card">
            <div className="testimonial-quote">
                <i className="fas fa-quote-left"></i>
            </div>
            <img src={img} alt={role} />
            <div className="testimonial-author">
                <div className="author-info">
                    <h4>{heading}</h4>
                    <p>{role}</p>
                </div>
            </div>
        </div>
    );
}
