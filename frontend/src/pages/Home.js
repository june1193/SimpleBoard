import React, { useState, useEffect } from 'react';
import { useAuth } from '../contexts/AuthContext';
import axios from 'axios';
import CreatePost from '../components/CreatePost';

const Home = () => {
  const { user, token } = useAuth();
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showCreateForm, setShowCreateForm] = useState(false);

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      const response = await axios.get('/api/posts');
      setPosts(response.data.data);
    } catch (error) {
      console.error('Failed to fetch posts:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleCreatePost = async (title, content) => {
    try {
      await axios.post('/api/posts', { title, content }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      setShowCreateForm(false);
      fetchPosts();
    } catch (error) {
      console.error('Failed to create post:', error);
      alert('게시글 작성에 실패했습니다.');
    }
  };

  const handleDeletePost = async (postId) => {
    if (!window.confirm('정말 삭제하시겠습니까?')) return;
    
    try {
      await axios.delete(`/api/posts/${postId}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchPosts();
    } catch (error) {
      console.error('Failed to delete post:', error);
      alert('게시글 삭제에 실패했습니다.');
    }
  };

  if (loading) {
    return <div>로딩 중...</div>;
  }

  return (
    <div>
      <h1>게시판</h1>
      
      {user && (
        <div style={{ marginBottom: '20px' }}>
          <button 
            className="btn btn-primary"
            onClick={() => setShowCreateForm(!showCreateForm)}
          >
            {showCreateForm ? '취소' : '게시글 작성'}
          </button>
        </div>
      )}

      {showCreateForm && (
        <CreatePost onSubmit={handleCreatePost} />
      )}

      <div>
        {posts.length === 0 ? (
          <p>게시글이 없습니다.</p>
        ) : (
          posts.map(post => (
            <div key={post.id} className="card">
              <div className="post-item">
                <div className="post-title">{post.title}</div>
                <div className="post-meta">
                  작성자: {post.username} | 
                  작성일: {new Date(post.createdAt).toLocaleDateString()}
                </div>
                <div className="post-content">{post.content}</div>
                {user && user.username === post.username && (
                  <div style={{ marginTop: '10px' }}>
                    <button 
                      className="btn btn-danger"
                      onClick={() => handleDeletePost(post.id)}
                    >
                      삭제
                    </button>
                  </div>
                )}
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Home; 