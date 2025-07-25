import React from 'react';
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav>
      <div className="nav-items"><Link to="/">Home</Link></div>
      <div className="nav-items"><Link to="/practice">Practice</Link></div>
      <div className="nav-items"><Link to="/about">About</Link></div>
    </nav>
  );
}
