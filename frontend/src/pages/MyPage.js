import React, { useState, useEffect } from 'react';
import { useAuth } from '../contexts/AuthContext';
import axios from 'axios';

const MyPage = () => {
  const { user, token } = useAuth();
  const [myPosts, setMyPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (user) {
      fetchMyPosts();
    }
  }, [user]);

  const fetchMyPosts = async () => {
    try {
      const response = await axios.get('/api/posts/my', {
        headers: { Authorization: `Bearer ${token}` }
      });
      setMyPosts(response.data.data);
    } catch (error) {
      console.error('Failed to fetch my posts:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleDeletePost = async (postId) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;
    
    try {
      await axios.delete(`/api/posts/${postId}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchMyPosts();
    } catch (error) {
      console.error('Failed to delete post:', error);
      alert('게시글 삭제에 실패했습니다.');
    }
  };

  if (!user) {
    return <div>로그인이 필요합니다.</div>;
  }

  if (loading) {
    return <div>로딩 중...</div>;
  }

  return (
    <div>
      <h1>마이페이지</h1>
      
      <div className="card">
        <h2>내 정보</h2>
        <p><strong>사용자명:</strong> {user.username}</p>
        <p><strong>이메일:</strong> {user.email}</p>
      </div>

      <div>
        <h2>내 게시글</h2>
        {myPosts.length === 0 ? (
          <p>작성한 게시글이 없습니다.</p>
        ) : (
          myPosts.map(post => (
            <div key={post.id} className="card">
              <div className="post-item">
                <div className="post-title">{post.title}</div>
                <div className="post-meta">
                  작성일: {new Date(post.createdAt).toLocaleDateString()}
                </div>
                <div className="post-content">{post.content}</div>
                <div style={{ marginTop: '10px' }}>
                  <button 
                    className="btn btn-danger"
                    onClick={() => handleDeletePost(post.id)}
                  >
                    삭제
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default MyPage; 