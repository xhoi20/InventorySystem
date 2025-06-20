package dto;


    public class UserResponse {
        private Long id;
        private String email;

        public UserResponse(Long id, String email) {
            this.id = id;
            this.email = email;
        }

        // Getters
        public Long getId() { return id; }
        public String getEmail() { return email; }
    }

