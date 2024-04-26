package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("ChatgptResponseDTO")
public class ChatgptResponseDTO {
//	{
//		  "id": "chatcmpl-9Hivp2hfoqnmdO2ko1g5z3sUfwS6s",
//		  "object": "chat.completion",
//		  "created": 1714011321,
//		  "model": "gpt-3.5-turbo-0125",
//		  "choices": [
//		    {
//		      "index": 0,
//		      "message": {
//		        "role": "assistant",
//		        "content": "이 텍스트의 요청과 응답은 총 1 토큰을 소모합니다."
//		      },
//		      "logprobs": null,
//		      "finish_reason": "stop"
//		    }
//		  ],
//		  "usage": {
//		    "prompt_tokens": 37,
//		    "completion_tokens": 31,
//		    "total_tokens": 68
//		  },
//		  "system_fingerprint": "fp_c2295e73ad"
//		}
	
	private String id;
	private String object;
	private String created;
	private String model;
	private List<ChatgptChoicesResponseDTO> choices;
	private ChatgptUsageResponseDTO2 usage;
	private String system_fingerprint;
	public ChatgptResponseDTO(String id, String object, String created, String model,
			List<ChatgptChoicesResponseDTO> choices, ChatgptUsageResponseDTO2 usage, String system_fingerprint) {
		super();
		this.id = id;
		this.object = object;
		this.created = created;
		this.model = model;
		this.choices = choices;
		this.usage = usage;
		this.system_fingerprint = system_fingerprint;
	}
	public ChatgptResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ChatgptResponseDTO [id=" + id + ", object=" + object + ", created=" + created + ", model=" + model
				+ ", choices=" + choices + ", usage=" + usage + ", system_fingerprint=" + system_fingerprint + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<ChatgptChoicesResponseDTO> getChoices() {
		return choices;
	}
	public void setChoices(List<ChatgptChoicesResponseDTO> choices) {
		this.choices = choices;
	}
	public ChatgptUsageResponseDTO2 getUsage() {
		return usage;
	}
	public void setUsage(ChatgptUsageResponseDTO2 usage) {
		this.usage = usage;
	}
	public String getSystem_fingerprint() {
		return system_fingerprint;
	}
	public void setSystem_fingerprint(String system_fingerprint) {
		this.system_fingerprint = system_fingerprint;
	}
	
	
}