package kr.ac.hansung.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product implements Serializable{

	private static final long serialVersionUID = 7696694810633931773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // hibernate sequential table을 만들지 않고 auto increment 필드 사용
	@Column(name = "product_id")
	private int id;

	@NotEmpty(message = "The product name must not be null")
	private String name;
	private String category;

	@Min(value = 0, message = "The product price must not be less than zero")
	private int price;

	@NotEmpty(message = "The manufacturer must not be null")
	private String manufacturer;

	@Min(value = 0, message = "The product unitInStock must not be less than zero")
	private int unitInStock;
	private String description;
	
	@Transient // DB에 저장하지 않고 File System에 저장
	private MultipartFile productImage;
	
	private String imageFilename; // DB에는 file경로만 넣는다.

}
