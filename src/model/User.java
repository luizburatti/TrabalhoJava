package model;
import java.util.List;

public class User {

	
	private Integer Idcollum;
	
	public Integer getId() 
	{
		return Idcollum;
		
		
	}
	
	//  Manipulando o dado nome 
	private String Namecollum;
	
	//Puxando o nome 
	
	public String getNome() 
	{
		return Namecollum;
		
		
	}
	
	/// Setando o nome  
	public void setNome (String nome) 
	{
	Namecollum = nome;	
		
	}
	
	//  Manipulando o dado CPF 
	private String CpfCollum;
	
	public String getCpf() 
	{
	   return  CpfCollum;
	}
	
	
	public void setCpf (String cpf)
	{
		
		CpfCollum = cpf;
		
	}
	
	
	private String EmailCollum;
	
	
	public String getEmail() 
	{
	   return  EmailCollum;
	}
	
	
	public void setEmail (String email)
	{
		
		EmailCollum = email;
		
	}
	
	
	
	@Override
	public String toString() 
	{
		return "User [Cpf=" + CpfCollum + ", Email=" + EmailCollum + ", Nome=" + Namecollum + "]";
		
	}
	
	
	public User(Integer id,String  cpf , String  email,String  nome )
	{
		super();
		
		Idcollum = id;
		CpfCollum =cpf;
		EmailCollum = email;
		Namecollum = nome;
	}
	
	
	
	public User() 
	{// Constructor stub
		
	}
	
	public User findUser (Integer id,List<User>users)
	
	{
		
		
		{
			for (User user :users)
			{
				if (user.getId().equals(Idcollum))
				{
					return user;
				}
				
			}
			
			
			return null;
		}
		
	}
			
			
			
			
	
	
	
	
	
}
