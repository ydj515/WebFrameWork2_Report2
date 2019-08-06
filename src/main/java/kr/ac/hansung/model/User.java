package kr.ac.hansung.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users") // security-contest.xml에서 "from users"라고 했기 때문에 table 이름을 바꿔 줘야함
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int id;

	@NotEmpty(message = "The username must not be null")
	private String username;

	@NotEmpty(message = "The password must not be null")
	private String password;

	@NotEmpty(message = "The email must not be null")
	private String email;

	@OneToOne(optional = false, cascade = CascadeType.ALL) // User를 저장할 때 자동적으로 shippingAddress도 저장
	@JoinColumn(unique = true)
	private ShippingAddress shippingAddress;

	@OneToOne(optional = false, cascade = CascadeType.ALL) // User를 저장할 때 자동적으로 cart도 저장 //optional=false의 의미 : 반드시 cart가 필요함
	@JoinColumn(unique = true)
	private Cart cart;
	
	private boolean enabled = false;

	private String authority;

}
