import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className="navbar">
      <div className="container">
        <div>
          <Link to="/" style={{ fontSize: '20px', fontWeight: 'bold' }}>
            게시판
          </Link>
        </div>
        <div>
          {user ? (
            <>
              <span>안녕하세요, {user.username}님!</span>
              <Link to="/mypage">마이페이지</Link>
              <button 
                onClick={handleLogout}
                style={{ 
                  background: 'none', 
                  border: 'none', 
                  color: 'white', 
                  cursor: 'pointer',
                  marginLeft: '20px'
                }}
              >
                로그아웃
              </button>
            </>
          ) : (
            <Link to="/login">로그인</Link>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar; 